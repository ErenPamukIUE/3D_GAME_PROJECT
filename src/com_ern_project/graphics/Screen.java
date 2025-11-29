package com_ern_project.graphics;

import java.util.Random;

public class Screen extends Render {
private Render test;
    public Screen(int width , int height) {
        super(width, height);
        Random random = new Random();
        int w = 256;
        int h = 256;
        test = new Render(w,h);
        for(int i = 0 ; i < w*h ; i++){
            test.pixels[i] = random.nextInt(0,5000);
        }
    }
    public void render() {
        for(int i =0 ; i<width*height ; i++) {
            pixels[i] = 0;
        }
        for(int i = 0 ; i<100 ; i++) {
            int anim = (int) (  (Math.sin((System.currentTimeMillis() + i) % 2000.0 / 2000 * Math.PI *2)) *200  );
            int anim2 = (int) (  (Math.cos((System.currentTimeMillis() + i) % 2000.0 / 2000 * Math.PI *2)) *200  );
            draw(test,(width - 256 ) / 2 + anim, (height - 256) /2 - anim2);

        }

    }

    public Render getTest() {
        return test;
    }

    public void setTest(Render test) {
        this.test = test;
    }
}
