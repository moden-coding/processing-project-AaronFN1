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
    int groundHeight = 475;
    int highscore = 0;

    // dino variables
    int dinoWidth = 30;
    int dinoHeight = 70;
    int dinoX;
    double dinoY;
    int dinoLeft;
    int dinoRight;
    double dinoBottom;
    double dinoTop;

    // cactus variables
    int cactusWidthA;
    int cactusWidthB;
    int cactusHeightA;
    int cactusHeightB;
    double cactusXA;
    double cactusXB;
    int cactusYA;
    int cactusYB;
    double cactusLeftA;
    double cactusLeftB;
    double cactusRightA;
    double cactusRightB;
    int cactusTopA;
    int cactusTopB;
    int cactusBottomA;
    int cactusBottomB;
    int cactusStart;
    double cactusSpeed;

    int scene;
    // scene 0 is the tutorial
    // scene 1 is the game start
    // scene 2 is the game
    // scene 3 is game over
    double score;
    double velocity;
    double acceleration;
    // double vertex = 1350;
    // double jumpHeight = 0;

    public void mousePressed() {
        if (scene == 2) {
            scene = 0;
        }
    }

    public void keyPressed() {
        if (key == ' ' || keyCode == UP) {
            if (dinoY >= 395) {
                // jump
                acceleration = .5;
                velocity = -10.4;
            }
        } else if (key == 'r' || key == 'R') {
            scene = 0;
        }
    }

    public void cactus() {
        fill(0, 255, 0);
        rect(Math.round(cactusXA), cactusYA, cactusWidthA, cactusHeightA);
        rect(Math.round(cactusXB), cactusYB, cactusWidthB, cactusHeightB);
        if (scene == 1) {
            cactusXA -= cactusSpeed;
            cactusXB -= cactusSpeed;
            if (cactusSpeed <= 25) {
                cactusSpeed = (double) (5 + (score / 300));
            }
        }
        if (cactusXA < 0) {
            cactusXA = cactusXB + (int) random((int) (300 + (cactusSpeed * 5)), (int) (1350 + (cactusSpeed * 5)));
            cactusHeightA = (int) random(35, 70);
            cactusYA = groundHeight - cactusHeightA;
            cactusWidthA = (int) random(25, 55);
        }
        if (cactusXB < 0) {
            cactusXB = cactusXA + (int) random((int) (300 + (cactusSpeed * 5)), (int) (1350 + (cactusSpeed * 5)));
            cactusHeightB = (int) random(35, 70);
            cactusYB = groundHeight - cactusHeightB;
            cactusWidthB = (int) random(25, 55);
        }
        cactusLeftA = cactusXA;
        cactusRightA = cactusXA + cactusWidthA;
        cactusTopA = cactusYA;
        cactusBottomA = cactusYA + cactusHeightA;
        cactusLeftB = cactusXB;
        cactusRightB = cactusXB + cactusWidthB;
        cactusTopA = cactusYA;
        cactusTopB = cactusYB;
        cactusBottomB = cactusYB + cactusHeightB;
    }

    public boolean collide(double leftObj1, double leftObj2, double rightObj1, double rightObj2, double topObj1,
            double topObj2, double bottomObj1, double bottomObj2) {
        if (rightObj1 >= leftObj2 && bottomObj1 >= topObj2 && leftObj1 <= (rightObj2 - 2)) {
            return true;
        } else {
            return false;
        }
    }

    public void draw() {
        // background
        background(255);

        // ground
        fill(0, 0, 0);
        rect(0, 475, 1350, 5);

        // reset / variable defining
        if (scene == 0) {
            dinoX = 75;
            dinoY = groundHeight - dinoHeight;
            cactusStart = 1350;
            cactusXA = cactusStart;
            cactusXB = cactusXA + (int) random((int) (300 + (cactusSpeed * 5)), (int) (1350 + (cactusSpeed * 5)));
            cactusHeightA = (int) random(35, 70);
            cactusWidthA = (int) random(25, 55);
            cactusHeightB = (int) random(35, 70);
            cactusWidthB = (int) random(25, 55);
            cactusYA = groundHeight - cactusHeightA;
            cactusYB = groundHeight - cactusHeightB;
            cactusSpeed = 5;

            score = 0;
            acceleration = 0;
            scene = 1;
            velocity = 0;
        }

        // dino
        fill(200, 0, 0);
        // strokeWeight(2);
        // stroke(0, 0, 0);
        // noFill();
        rect(dinoX, Math.round(dinoY), dinoWidth, dinoHeight);

        // // find jump height
        // if (dinoY < vertex){
        // vertex = dinoY;
        // jumpHeight = (groundHeight - dinoHeight) - vertex;
        // System.out.println(jumpHeight);
        // }

        // gravity
        if (dinoY <= (groundHeight - dinoHeight)) {
            if (scene == 1) {
                dinoY += velocity;
                velocity += acceleration;
            }
        }

        // keep dino on gorund
        if (dinoY > (groundHeight - dinoHeight)) {
            dinoY = (groundHeight - dinoHeight);
        }

        dinoLeft = dinoX;
        dinoRight = dinoX + dinoWidth;
        dinoBottom = dinoY + dinoHeight;
        dinoTop = dinoY;

        // reset velocity and acceleration for efficiency
        if (dinoY == (groundHeight - dinoHeight)) {
            velocity = 0;
            acceleration = 0;
        }

        // cactus
        cactus();

        if (collide(dinoLeft, cactusLeftA, dinoRight, cactusRightA, dinoTop, cactusTopA, dinoBottom,
                cactusBottomA) == true) {
            scene = 2;
        }
        if (collide(dinoLeft, cactusLeftB, dinoRight, cactusRightB, dinoTop, cactusTopB, dinoBottom,
                cactusBottomB) == true) {
            scene = 2;
        }

        // score
        if (scene == 1) {
            score++;
        }
        if (scene == 1 || scene == 2) {
            fill(0, 0, 0);
            textSize(25);
            textAlign(RIGHT);
            text("HI: " + highscore + "        " + (int) score, width - 10, 35);
        }
        if (scene == 2 && score > highscore) {
            highscore = (int) score;
        }

        if (scene == 2) {
            textSize(50);
            textAlign(CENTER);
            text("You lose! Click to restart", width / 2, 250);
        }
    }
}
