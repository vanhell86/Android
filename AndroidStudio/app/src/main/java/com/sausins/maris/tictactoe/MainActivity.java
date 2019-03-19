package com.sausins.maris.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn[]; //seit glabasies pogas. Tapat ka taisijam pa vienam maingajam. Tas tapec, lai var ar ciklu iet cauri
    private Switch checkPlayer; //sledzis, ar to vizuali paradisim, kuram speletajam jaiet
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //savienojam ar xml failu

        btn = new Button[9]; //pasakam, cik liels masivs
        btn[0] = findViewById(R.id.field1); //aizpildam masivu ar pogam no xml faila
        btn[1] = findViewById(R.id.field2);
        btn[2] = findViewById(R.id.field3);
        btn[3] = findViewById(R.id.field4);
        btn[4] = findViewById(R.id.field5);
        btn[5] = findViewById(R.id.field6);
        btn[6] = findViewById(R.id.field7);
        btn[7] = findViewById(R.id.field8);
        btn[8] = findViewById(R.id.field9);

        checkPlayer = findViewById(R.id.choiceSwitch); //pasakam, ka sledzis ir tas sledzis, kurs ir xml faila
        checkPlayer.setClickable(false);
    }

    public void buttonClicked(View view) { //izsaucam move() funkciju. -to chosenButton: vins genere automatiski, mes iekavas rakstam vnk 0,1,2...
        switch(view.getId()){
            case R.id.field1:
                move(0);
                break;
            case R.id.field2:
                move(1);
                break;
            case R.id.field3:
                move(2);
                break;
            case R.id.field4:
                move(3);
                break;
            case R.id.field5:
                move(4);
                break;
            case R.id.field6:
                move(5);
                break;
            case R.id.field7:
                move(6);
                break;
            case R.id.field8:
                move(7);
                break;
            case R.id.field9:
                move(8);
                break;

        }

        boolean win = checkWin(); //izsaucam parbaudi vai speletajs uzvar

        if(win){ //ja uzvar izsaucam endgame
            endGame();
        }

    }

    private void move(int chosenButton){


        if(checkPlayer.isChecked()){ //parbaudam, kurs gajiens- X, vai 0. To dabujam no sledza true = 0, false = X
            btn[chosenButton].setText("O");
            checkPlayer.setChecked(false);
        }else{
            btn[chosenButton].setText("X");
            checkPlayer.setChecked(true);
        }

        btn[chosenButton].setEnabled(false); //disablojam pogu, uz kuras uzspiez
        count++;
        if (count == 9){
            Toast.makeText(getApplicationContext(),"Spele beigusies, uzvar draudziba " ,Toast.LENGTH_LONG).show(); //izvadam, kurs ir uzvarejis
        }

    }

    public void newGame(View view){
        for(int i = 0; i < btn.length; i++){ //izejam cauri visam masivam, lai cilveks var spelet atkal
            btn[i].setText("");
            btn[i].setEnabled(true);

        }
        count = 0;
    }

    private boolean checkWin(){
        for(int i = 0; i < btn.length; i+=3){
            String row = btn[i].getText().toString() + btn[i+1].getText().toString() + btn[i+2].getText().toString();
            if(row.equals("OOO") || row.equals("XXX")){
                return  true;
            } //parbaudam rindas


        }

        for(int i = 0; i < 3; i++){
            String column = btn[i].getText().toString() + btn[i+3].getText().toString() + btn[i+6].getText().toString();
            if(column.equals("OOO") || column.equals("XXX")){
                return true;
            }
        }//parbaudam kolonas

        String diag = btn[0].getText().toString() + btn[4].getText().toString() + btn[8].getText().toString();

        if(diag.equals("XXX") || diag.equals("OOO")){
            return true;
        }//parbaudam vienu diognali
        diag = btn[2].getText().toString() + btn[4].getText().toString() + btn[6].getText().toString();

        if(diag.equals("XXX") || diag.equals("OOO")){
            return true;
        }//parbaudam otru diognali
        return false;
    }

    private void endGame(){

        int count = 0;
        for(int i = 0; i < btn.length; i++){
            btn[i].setEnabled(false); //izejam cauri visam masivam un izsledzam pogas
        }


        String player;


        if(checkPlayer.isChecked()){
            player = "X";
        }else{
            player = "O";
        }

        checkPlayer.setChecked(false); // no 134-142 rindai- parbaudam, kurs speletajs uzvarejis

        Toast.makeText(getApplicationContext(),"Spele beigusies, uzvar " + player  ,Toast.LENGTH_LONG).show(); //izvadam, kurs ir uzvarejis
    }
}
