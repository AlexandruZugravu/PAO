/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Radu-Stefan Neacsu
 */
public class MessageListener implements Runnable
{
    private Scanner socketReader;
    private MessageHandler messageHandler;
    
    public MessageListener(Scanner buff)
    {
        socketReader = buff;
        messageHandler = MessageHandler.getInstance();
        workerThread = new Thread(this);
    }
    
    
    private Thread workerThread;
  
    public void start()
    {
        workerThread.start();
    }
    
    @Override
    public void run()
    {
        String line;
        while (true)
        {
            line = socketReader.nextLine();
            if(line == null)
            {
                System.out.println("Connection closed !!! Close the fucking socket !!");
                break;
            }
            messageHandler.addMessageTask(new MessageTask(line));
            messageHandler.releaseSemaphore();
        }
    }   
}
