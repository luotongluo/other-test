package com.lt.dailytest.current;

import javax.swing.text.rtf.RTFEditorKit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author tong.luo
 * @description WorkMasterTest
 * @date 2021/3/11 15:28
 */
public class WorkMasterTest {
    //任务队列
    protected Queue<Object> workQueue = new ConcurrentLinkedDeque<>();
    //worker进程队列
    protected Map<String, Thread> threadMap = new HashMap<>();
    //子任务处理结果集
    protected Map<String, Object> resultMap = new ConcurrentHashMap<>();

    /**
     * 是否所有的子任务都结束了
     *
     * @return
     */
    public boolean isComplete() {
        for (Map.Entry<String, Thread> stringThreadEntry : threadMap.entrySet()) {
            if (stringThreadEntry.getValue().getState() != Thread.State.TERMINATED) {
                return false;
            }
        }
        return true;
    }

    /**
     * master的构造韩树，需要一个worke进程逻辑，和需要的woker进程数量
     *
     * @param woker
     * @param countWorker
     */
    public void WorkMasterTest(Woker woker, int countWorker) {
        woker.setWorkQueue(workQueue);
        woker.setResultMap(resultMap);

        for (int i = 0; i < countWorker; i++) {
            threadMap.put(Integer.toString(i), new Thread(woker, Integer.toString(i)));
        }

    }

    /**
     * 提交一个任务
     *
     * @param obj
     */
    public void submit(Object obj) {
        workQueue.add(obj);
    }

    /**
     * 返回处理结果
     *
     * @return
     */
    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    /**
     * 开始运行所有的worker进行，进行处理
     */
    public void execute() {
        for (Map.Entry<String, Thread> threadEntry : threadMap.entrySet()) {
            threadEntry.getValue().start();
        }
    }

    public static void main(String[] args) {
        WorkMasterTest work = new WorkMasterTest();
        work.WorkMasterTest(new PlusWorker(), 5);
        for (int i = 0; i < 100; i++) {
            work.submit(i);
        }
        work.execute();
        int re =0;
        Map<String, Object> resultMap = work.getResultMap();
        //不需要等待所有的worker都执行完
        while (resultMap.size() > 0 || !work.isComplete()){
            Set<Map.Entry<String, Object>> entries = resultMap.entrySet();
            String key = null;
            //开始计算最终结果
            for (String k : resultMap.keySet()) {
                key = k;
                break;
            }
            Integer i = null;
            if(null != key){
                i = (Integer) resultMap.get(key);
            }
            if(null != i){
                re +=i;
            }
            if(null != key){
                //移除已经被计算过的项
                resultMap.remove(key);
            }
        }
        System.out.println(re);


    }
}
