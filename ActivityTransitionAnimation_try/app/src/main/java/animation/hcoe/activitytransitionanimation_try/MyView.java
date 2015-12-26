package animation.hcoe.activitytransitionanimation_try;

import android.content.Context;
import android.graphics.Canvas;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

import static java.lang.StrictMath.random;

/**
 * Created by sunil on 8/26/15.
 */
public class MyView extends View {

    static public boolean moveBarRight=true;

    static public double barMovingRate=1;
    static public int ballX=0,ballY=0;
    static public int barX=0,barY=0;
    static public int barLength=500,barHeight=100;
    static public int barMarginButtom=0;
    static public int ballRadi=100;
    static public int ballSpeedX=20,ballSpeedY=-ballSpeedX+3,barSpeed=10;

    int tmpBallSpeedX=0,tmpBallSpeedY=0;

    int ballSpeedUp=30;
    int ballSpeedDown=5;

    public MyView(Context context) {
        super(context);
    }




    @Override
    protected void onDraw(final Canvas canvas) {

        super.onDraw(canvas);
        ballX=canvas.getWidth()/2-ballRadi-50;
        ballRadi=canvas.getWidth()/9;
        ballSpeedUp = ballRadi/7;
        ballSpeedDown = ballRadi/15;

        Rect rect = new Rect();
        rect.set(5, 5, canvas.getWidth() - 5, canvas.getHeight() - 30);

        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);

        canvas.drawRect(rect, paint);

        drawButtumBars(canvas);

        drawAndMoveBall(canvas);

        //moveBar(canvas);

        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (ballY > 0 + ballRadi + ballSpeedUp) {
                    ballY -= ballSpeedUp;
                } else {
                    ballY = 0+ballRadi + ballSpeedUp+ballSpeedDown;
                }
                return true;
            }
        });
        invalidate();
    }

    private void drawAndMoveBall(Canvas canvas) {
        //////for ball ///////
        Paint ball= new Paint();
        ball.setColor(Color.RED);
        ball.setStyle(Paint.Style.FILL);

        RectF rectF=new RectF();


/*
        tmpBallSpeedX=ballSpeedX;
        tmpBallSpeedY=ballSpeedY;
*/

        /*if(ballX > canvas.getWidth()-ballRadi-5){
            tmpBallSpeedX = (ballSpeedX<0?ballSpeedX:(-1*ballSpeedX));
        }else if(ballX < 0+ballRadi+5 ){
            tmpBallSpeedX = (ballSpeedX<0?(-1*ballSpeedX):ballSpeedX);
        }
        ballX += tmpBallSpeedX;

        if(ballY > barY-barHeight/2-ballRadi){//canvas.getHeight()-ballRadi-5){
            if(ballX-ballRadi <= barX+barLength/2 && ballX+ballRadi >= barX-barLength/2) {
                tmpBallSpeedY = (ballSpeedY < 0 ? ballSpeedY : (-1 * ballSpeedY));
            }
            else {
                tmpBallSpeedX = 0;
                tmpBallSpeedY = 0;
            }
        }else if(ballY < 0+ballRadi+5 ){
            tmpBallSpeedY = (ballSpeedY<0?(-1*ballSpeedY):ballSpeedY);
        }
        ballY += tmpBallSpeedY;*/

        if(ballY<canvas.getHeight()-ballRadi - 25) {
            ballY += ballSpeedDown;
        }else {
            Toast.makeText(getContext(), "Oh No!!.", Toast.LENGTH_SHORT).show();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ballY = 0+ballRadi + ballSpeedUp+ballSpeedDown;
        }



        rectF.set(ballX - ballRadi, ballY - ballRadi, ballX + ballRadi, ballY + ballRadi);
        canvas.drawOval(rectF, ball);
////////////

    }

    private void moveBar(Canvas canvas) {
        ///////for bar////////
        /*if(barX > canvas.getWidth()-barLength/2-5){
            barSpeed = (barSpeed<0?barSpeed:(-1*barSpeed));
        }else if(barX < 0+barLength/2+5 ){
            barSpeed = (barSpeed<0?(-1*barSpeed):barSpeed);
        }*/
        int tmpSpeed=barSpeed;
        if(moveBarRight==true && barX  < canvas.getWidth()){
            tmpSpeed = (barSpeed<0?(-1*barSpeed):barSpeed);
        }else if(moveBarRight==false && barX > 0){
            tmpSpeed = (barSpeed<0?barSpeed:(-1*barSpeed));
        }else {
            tmpSpeed=0;
        }


        barX += (int)tmpSpeed*barMovingRate;
        barY=0 + canvas.getHeight() - barHeight/2 - barMarginButtom;
        Rect bar = new Rect();
        bar.set(barX - barLength / 2, barY - barHeight / 2, barX + barLength / 2, barY + barHeight / 2);
        Paint paintForBar = new Paint();
        paintForBar.setColor(Color.GREEN);
        paintForBar.setStyle(Paint.Style.FILL);
        canvas.drawRect(bar, paintForBar);
////////

    }

    private void drawButtumBars(Canvas canvas){

        int barLenth=150;
        for(int i=0;i<5;i++) {
            Rect rect = new Rect();
            rect.set(barLenth * i + 5, canvas.getHeight() - (new Random().nextInt(canvas.getHeight()/i)), barLenth * (i + 1), canvas.getHeight() - 30);

            Paint paint = new Paint();
            paint.setColor(Color.green((int) random() % 5));
            paint.setStyle(Paint.Style.FILL);

            canvas.drawRect(rect, paint);
        }
    }
}
