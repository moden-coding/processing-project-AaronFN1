public class Cactus {
    private int x;
    private int y;
    private int width;
    private int height;


    public void move(){
        x-= 5;
        if(x > 500){
            x = 0;
        }
    }
}
