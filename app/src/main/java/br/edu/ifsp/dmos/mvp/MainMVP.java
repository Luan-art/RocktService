package br.edu.ifsp.dmos.mvp;

import android.content.Context;
import android.view.MotionEvent;

public interface MainMVP {

    interface View{
        Context getContext();
    }

    interface Presenter{

        void detach();

        void toch(MotionEvent motionEvent);



    }
}
