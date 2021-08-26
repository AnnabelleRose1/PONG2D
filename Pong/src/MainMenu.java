import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MainMenu extends JFrame implements Runnable{

    public Graphics2D g2;
    public KL keyListener = new KL();
    public ML mouseListener = new ML();
    public Text startGame, exitGame, pong;
    public boolean isRunning = true;


    public MainMenu() {
        // Setting for the window
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.SCREEN_TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.addKeyListener(keyListener);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);

        this.startGame = new Text("Start Game", new Font("Time New Roman",Font.PLAIN, Constants.TEXT_SIZE),Constants.SCREEN_WIDTH /2.0 - 140.0, Constants.SCREEN_HEIGHT /2.0, Color.WHITE);
        this.exitGame = new Text("Exit", new Font("Times New Roman", Font.PLAIN, Constants.TEXT_SIZE),Constants.SCREEN_WIDTH / 2.0 - 80.0, Constants.SCREEN_HEIGHT / 2.0 + 60, Color.WHITE);
        this.pong = new Text("PONG", new Font("Times New Roman", Font.BOLD,100), Constants.SCREEN_WIDTH / 2.0 - 155,200, Color.WHITE);

        g2 = (Graphics2D)getGraphics();
    }
    public void update (double dt) {
        Image dbImage = createImage(getWidth(),getHeight());
        Graphics dbg = dbImage.getGraphics();
        this.draw(dbg);
        g2.drawImage(dbImage,0,0,this);

        if(mouseListener.getMouseX() > startGame.x && mouseListener.getMouseX() < startGame.x + startGame.width && mouseListener.getMouseY() > startGame.y - startGame.height / 2 && mouseListener.getMouseY() < startGame.y + startGame.height / 2) {
            startGame.color = Color.GRAY;

            if(mouseListener.isMousePressed()) {
                Main.changeState(1);
            }
        } else {
            startGame.color = Color.WHITE;
        }

        if(mouseListener.getMouseX() > exitGame.x && mouseListener.getMouseX() < exitGame.x + exitGame.width && mouseListener.getMouseY() > exitGame.y - startGame.height / 2 && mouseListener.getMouseY() < exitGame.y + exitGame.height / 2) {
            exitGame.color = Color.GRAY;
            if (mouseListener.isMousePressed()) {
                Main.changeState(2);
            }
        } else {
            exitGame.color = Color.WHITE;
        }
    }

    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0,0,Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);


        pong.draw(g2);
        startGame.draw(g2);
        exitGame.draw(g2);
    }

    public void stop() {
        isRunning = false;
    }
    public void run() {
        double lastFrameTime = 0.0;
        while (isRunning) {
            double time = Time.getTime();
            double deltaTime = time - lastFrameTime;
            lastFrameTime = time;

            update(deltaTime);
        }
        this.dispose();
        return;
    }
}
