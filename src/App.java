import processing.core.*;
import processing.event.KeyEvent;

public class App extends PApplet {
    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
        background(255, 255, 255);
        noStroke();

    }

    public void settings() {
        size(1350, 800);

    }

    // defining variables
    int scene = 1;
    // scene 0 is the tutorial
    // scene 1 is the game
    // scene 2 is game over
    int score = 0;
    double velocity;
    double dinoHeight = 405;
    boolean jump = false;
    double acceleration = 0;
    double cactusPosition = 1350;
    double vertex = 1350;
    double jumpHeight = 0;

    public void keyPressed() {
        if (key == ' ' || keyCode == UP) {
            if (dinoHeight >= 395) {
                // jump
                acceleration = .5;
                velocity = -10.4;
            }
        }
    }

    public void draw() {
        // background
        background(255);

        // ground
        fill(0, 0, 0);
        rect(0, 475, 1350, 5);

        // dino
        fill(200, 0, 0);
        rect(50, Math.round(dinoHeight), 40, 70);

        // // find jump height
        // if (dinoHeight < vertex){
        //     vertex = dinoHeight;
        //     jumpHeight = 405 - vertex;
        //     System.out.println(jumpHeight);
        // }

        //gravity
        if (dinoHeight <= 405) {
            dinoHeight += velocity;
            velocity += acceleration;
        }


        // keep dino on gorund
        if (dinoHeight > 405) {
            dinoHeight = 405;
        }

        //reset velocity and acceleration for efficiency
        if (dinoHeight == 405){
            velocity = 0;
            acceleration = 0;
        }

        //cactus
        fill(0, 255, 0);
        rect(Math.round(cactusPosition), 407, 30, 68);
        cactusPosition -= 3;
        if (cactusPosition < 0){
            cactusPosition = 1350;
        }

        // score
        if (scene == 1) {
            score++;
        }
        if (scene == 1 || scene == 2) {
            fill(0, 0, 0);
            textSize(25);
            text(score, 1250, 35);
        }
    }
}
