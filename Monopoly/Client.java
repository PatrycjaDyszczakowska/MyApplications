package com.example.patrycja.monopolygame;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Queue;

import android.os.AsyncTask;
import android.util.Log;

/**
 * @author Patrycja Dyszczakowska
 * Klasa odpowiadająca za polączenie asynchroniczne klienta z serwerem 
 */
public class Client extends AsyncTask<Void, Void, Void> {
	
    String dstAddress;
    int dstPort;
    BufferedReader inputStream;
    static PrintStream out;
    private static Queue<String> queue = new LinkedList<String>();
    private static Queue<String> queueRes = new LinkedList<String>();
    private boolean interrupted = false;
    private String TAG = getClass().getName();
	/**
	 * Konstruktor klasy Client odpowiadający za 
	 * adres oraz port polączenia z serwerem
	 */
    Client(String addr, int port) {
        dstAddress = addr;
        dstPort = port;
    }
	/**
	 * Metoda odpowiadająca za polaczenie sie z serwerem
	 */
    @Override
    protected Void doInBackground(Void... arg0) {

        Socket socket = null;
        try {
            socket = new Socket(dstAddress, dstPort);
            out = new PrintStream(socket.getOutputStream());
            inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while(true){
                try {
                    String res;
                    if(!(res=inputStream.readLine()).equals("")) {
                        queueRes.add(res);
                    }
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
	/**
	 * Metoda odpowiadająca za wysyłanie wiadomości do serwera
	 */
    public static synchronized void SendMessage(String msg){
        try {
            //queue.add(msg);
            out.println(msg);
            Log.d("MSG:", msg);
        }
        catch(IllegalStateException e){
            e.printStackTrace();
        }

    }
	/**
	 * Metoda odbierająca wiadomości z serwea 
	 */
    public static synchronized String RereiweMesaage(){
        return queueRes.poll();
    }
}