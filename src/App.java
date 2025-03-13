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
    // dino variables
    int dinoWidth = 30;
    int dinoHeight = 70;
    int dinoX;
    double dinoY;
    int dinoLeft;
    int dinoRight;
    double dinoBottom;
    double dinoTop;
    
    //cactus variables
    int cactusWidth = 40;
    int cactusHeight = 68;
    double cactusX;
    int cactusY;
    double cactusLeft;
    double cactusRight;
    int cactusTop;
    int cactusBottom;
    int cactusStart;

    int scene;
    // scene 0 is the tutorial
    // scene 1 is the game start
    // scene 2 is the game
    // scene 3 is game over
    int score;
    double velocity;
    double acceleration;
    // double vertex = 1350;
    // double jumpHeight = 0;

    public void keyPressed() {
        if (key == ' ' || keyCode == UP) {
            if (dinoY >= 395) {
                // jump
                acceleration = .5;
                velocity = -10.4;
            }  
        }
        else if (key == 'r' || key == 'R'){
            scene = 0;
        }
    }
    
    public boolean collide(double leftObj1, double leftObj2, double rightObj1, double rightObj2, double topObj1, double topObj2, double bottomObj1, double bottomObj2){
        if (rightObj1 >= leftObj2 && bottomObj1 >= topObj2 && leftObj1 <= (rightObj2-2)){
            return true;
        }
        else {
            return false;
        }
    }

    public void draw() {
        // background
        background(255);

        // ground
        fill(0, 0, 0);
        rect(0, 475, 1350, 5);

        //reset / variable defining
        if (scene == 0){
            dinoX = 75;
            dinoY = groundHeight - dinoHeight;
            cactusStart = 1350;
            cactusX = cactusStart;
            cactusY = groundHeight - cactusHeight;
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
        //     vertex = dinoY;
        //     jumpHeight = (groundHeight - dinoHeight) - vertex;
        //     System.out.println(jumpHeight);
        // }

        //gravity
        if (dinoY <= (groundHeight - dinoHeight)) {
            if (scene == 1){
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

        //reset velocity and acceleration for efficiency
        if (dinoY == (groundHeight - dinoHeight)){
            velocity = 0;
            acceleration = 0;
        }

        //cactus
        fill(0, 255, 0);
        // strokeWeight(2);
        // noFill();
        rect(Math.round(cactusX), cactusY, cactusWidth, cactusHeight);
        if (scene == 1){
        cactusX -= 5;
        }
        if (cactusX < 0){
            cactusX = cactusStart;
        }

        cactusLeft = cactusX;
        cactusRight = cactusX + cactusWidth;
        cactusTop = cactusY;
        cactusBottom = cactusY + cactusHeight;

        if (collide(dinoLeft, cactusLeft, dinoRight, cactusRight, dinoTop, cactusTop, dinoBottom, cactusBottom) == true ){
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
            text(score, width - 10, 35);
        }
        //add highscore that updates at game end
        if (scene == 2){
            textSize(50);
            textAlign(CENTER); 
            text("You lose! Press R to restart", width / 2, 250);
        }
    }
}
