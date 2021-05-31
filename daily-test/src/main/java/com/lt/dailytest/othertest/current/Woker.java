package com.lt.dailytest.othertest.current;

import java.util.Map;
import java.util.Queue;

/**
 * @author tong.luo
 * @description Woker
 * @date 2021/3/11 15:34
 */
public class Woker implements Runnable {
    //任务队列，用于取得子任务
    protected Queue<Object> workQueue;
    //子任务处理结果集
    protected Map<String, Object> resultMap;

    public Queue<Object> getWorkQueue() {
        return workQueue;
    }

    public void setWorkQueue(Queue<Object> workQueue) {
        this.workQueue = workQueue;
    }

    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    /**
     * 子任务处理的逻辑，在子类中实现具体逻辑
     *
     * @param input
     * @return
     */
    public Object handle(Object input) {
        return input;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        while (true) {
            Object poll = workQueue.poll();
            if (poll == null) {
                break;
            }
            //处理子任务
            Object handle = handle(poll);
            //将结果写入返回结果集中
            resultMap.put(Integer.toString(poll.hashCode()), handle);
        }
    }
}
