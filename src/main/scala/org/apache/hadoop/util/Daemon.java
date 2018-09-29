package org.apache.hadoop.util;

public class Daemon extends Thread{
    {
        setDaemon(true);
    }

    Runnable runnable = null;

    public Daemon(){
        super();
    }

    public Daemon(Runnable runnable){
        super();
        this.runnable = runnable;
        this.setName(((Object) runnable).toString());
    }

    public Runnable getRunable(){
        return runnable;
    }
}
