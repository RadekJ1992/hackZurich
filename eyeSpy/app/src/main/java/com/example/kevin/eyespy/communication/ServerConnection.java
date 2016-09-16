package com.example.kevin.eyespy.communication;

/**
 * Created by kevin on 9/17/16.
 *
 *
 */

import android.os.Handler;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ServerConnection implements Runnable {

    private final InetAddress mDestination;
    private final int mPort;
    private final Handler mHandler = new Handler();


    public ServerConnection (InetAddress destination, int port) {
        mDestination = destination;
        mPort = port;
    }



    @Override
    public void run() {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(mDestination, mPort));
        } catch (IOException e) {

        }
    }

}