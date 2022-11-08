package com.snake.libgdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

public class Game_old extends ApplicationAdapter {
    final private float TILE_SIZE = 16; // The size of the snake, apple and other objects
    final private float MOVE_FREQUENCY = 2000000; // How often the snake moves, in nanoseconds
    final private float MOVE_LENGTH = 5; // How many pixels the snake moves each time
    final private int WINDOW_WIDTH = 800;
    final private int WINDOW_HEIGHT = 480;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Texture snakeImage;
    private Texture appleImage;
    private Array<Rectangle> snake;
    private Rectangle apple;
    private String direction = "";
    private float xPreviousPosition;
    private float yPreviousPosition;
    private long lastMoveTime = TimeUtils.nanoTime();


    private void moveSnakeHead() {
        // save the position of the head of the snake
        xPreviousPosition = snake.get(0).x;
        yPreviousPosition = snake.get(0).y;

        // then move the head of the snake
        if(direction.equals("right")) snake.get(0).x += MOVE_LENGTH;
        if(direction.equals("left")) snake.get(0).x -= MOVE_LENGTH;
        if(direction.equals("up")) snake.get(0).y += MOVE_LENGTH;
        if(direction.equals("down")) snake.get(0).y -= MOVE_LENGTH;
    }

    private void moveSnakeBody() {
        // save position of each rectangle before moving
        // each rectangle in the snake body array
        for (int i = 1; i < snake.size; i++) {
            float x = snake.get(i).x;
            float y = snake.get(i).y;
            snake.get(i).x = xPreviousPosition;
            snake.get(i).y = yPreviousPosition;
            xPreviousPosition = x;
            yPreviousPosition = y;
        }
    }

    private void expandSnake() {
        Rectangle snakeRect = new Rectangle();
        snakeRect.width = TILE_SIZE;
        snakeRect.height = TILE_SIZE;
        snakeRect.x = snake.get(0).x;
        snakeRect.y = snake.get(0).y;
        snake.add(snakeRect);
    }

    private void respawnApple() {
        apple.x = MathUtils.random(0, 800-TILE_SIZE);
        apple.y = MathUtils.random(0, 480-TILE_SIZE);
    }

    private boolean isOutOfBounds(Rectangle rect) {
        if (rect.x < 0 || rect.x > WINDOW_WIDTH - rect.width || rect.y < 0 || rect.y > WINDOW_HEIGHT - rect.width) {
            return true;
        }
        return false;
    }

    private void reset() {
        if (snake.size > 1) snake.removeRange(1, snake.size-1);
        snake.get(0).x = WINDOW_WIDTH / 2 - TILE_SIZE / 2;
        snake.get(0).y = WINDOW_HEIGHT / 2 - TILE_SIZE / 2;
        direction = "";
    }

    @Override
    public void create () {
        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();

        // load the images for the snake and the apple
        snakeImage = new Texture(Gdx.files.internal("snake.png"));
        appleImage = new Texture(Gdx.files.internal("apple.png"));

        // create the snake array and spawn the snake
        snake = new Array<Rectangle>();
        Rectangle snakeHead = new Rectangle();
        snakeHead.x = WINDOW_WIDTH / 2 - TILE_SIZE / 2;
        snakeHead.y = WINDOW_HEIGHT / 2 - TILE_SIZE / 2;
        snakeHead.width = TILE_SIZE;
        snakeHead.height = TILE_SIZE;
        snake.add(snakeHead);

        // create and spawn the first apple
        apple = new Rectangle();
        apple.width = TILE_SIZE;
        apple.height = TILE_SIZE;
        respawnApple();
    }

    @Override
    public void render () {
        // clear the screen with a color. The
        // arguments to clear are the red, green, blue and alpha
        // component in the range [0,1] of the color
        // to be used to clear the screen.
        ScreenUtils.clear(0.7f, 0.7f, 0.9f, 0);

        // tell the camera to update its matrices
        camera.update();

        // tell the SpriteBatch to render in the coordinate system used by the camera
        batch.setProjectionMatrix((camera.combined));

        // begin a new batch and draw the snake, apple and text
        batch.begin();
        batch.draw(appleImage, apple.x, apple.y);
        for (Rectangle snakeRect : snake) {
            batch.draw(snakeImage, snakeRect.x, snakeRect.y);
        }
        batch.end();

        // process user input
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && !direction.equals("right")) {
            direction = "left";
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && !direction.equals("left")) {
            direction = "right";
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && !direction.equals("down")) {
            direction = "up";
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && !direction.equals("up")) {
            direction = "down";
        }

        // check if it's time to move the snake
        if (TimeUtils.nanoTime() - lastMoveTime > MOVE_FREQUENCY) {
            lastMoveTime = TimeUtils.nanoTime();
            moveSnakeHead();
            moveSnakeBody();
        }

        // respawn apple and expand snake if snake collides with apple
        if (snake.get(0).overlaps(apple)) {
            respawnApple();
            expandSnake();
        }

        // reset the game if the snake collides with the wall or itself
        if (isOutOfBounds(snake.get(0))) {
            reset();
        }
    }

    @Override
    public void dispose () {
        snakeImage.dispose();
        appleImage.dispose();
        batch.dispose();
    }
}
