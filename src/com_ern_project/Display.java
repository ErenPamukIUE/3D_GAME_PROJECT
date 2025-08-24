package com_ern_project;
import com_ern_project.graphics.Render;
import com_ern_project.graphics.Screen;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;

public class Display extends Canvas implements Runnable {
    public static final int WIDTH =800;
    public static final int HEIGHT =600;
    public static final String TITLE="whasThis";

    private Thread thread;
    private boolean running = false;
    private BufferedImage ing;
    private Render render;
    private Screen screen;
    private int[] pixels;

    public Display() {
        screen = new Screen(WIDTH,HEIGHT);
        ing = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt)ing.getRaster().getDataBuffer()).getData();
    }

    public void start(){
        if (running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
        System.out.println("Working");
    }
    public void run(){
        while(running) {
            tick();
            render();
        }
    }
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            createBufferStrategy(3);
            return;
        }
        screen.render();

        for(int i = 0 ; i<WIDTH*HEIGHT ; i++) {
            pixels[i] = screen.pixels[i];
        }
        Graphics g = bs.getDrawGraphics();
        g.drawImage(ing,0,0,WIDTH,HEIGHT,null);
        g.dispose();
        bs.show();
    }
    private void tick() {

    }
    private void stop() {
        if (!running) return;
        running = false;
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
Display game = new Display();
JFrame frame = new JFrame();
frame.add(game);
frame.pack();
frame.setTitle(TITLE);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setResizable(false);
frame.setVisible(true);
frame.setSize(WIDTH,HEIGHT);
frame.setLocationRelativeTo(null);



System.out.println("Running...");
game.start();


    }
}