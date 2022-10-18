package src;

interface Q<T>{//Queue 구현을 위한 인터페이스

    boolean isFull();
    boolean isEmpty();
    void enqueue(T t);
    T dequeue();
    T peek();
    int size();
    void clear();

}

class CashierTask extends Thread { // 점원 작업 단위(Thread)
    // TODO: your code here
    private Cashier cashier;
    private int customer;
    CashierTask(Cashier cashier, int customer){//cashier 와 손님작업물을 받아옴
        this.cashier = cashier;
        this.customer = customer;
    }

    public int getCustomer(){ // 테스트를 위한 getCustomer 메서드
        return customer;
    }
     public void run(){//Run Thread
        try{
            //System.out.println("start work: " + customer);//일 시작 check
            Thread.sleep(this.customer*100);//인자로 받은 작업물*100
            //System.out.println("end work: " + customer);//일 종료 check
        }catch (InterruptedException e){
            System.out.println(e);
        }
        this.cashier.setWork(false);//스레드가 종료되면 work status 를 false로 만듦
    }
}

class Cashier {//점원 클래스
    // TODO: your code here
    private boolean isWork = false;//점원의 work status
    public void setWork(boolean work){
        this.isWork = work;
    } //work status 반영
    public boolean isWorking() {
        return this.isWork;
    } // return work status

}

class Queue <T> implements Q <T>{//제네릭 큐 정의, implement Q
    // TODO: your code here
    private int front;//시작 인덱스
    private int rear;//끝 인덱스
    private int size;//개수
    private Object[] queue; // 배열
    public Queue (int size){//사이즈가 정해질 경우
        this.front = -1;
        this.rear = -1;
        this.size = size;
        this.queue = new Object [size];
    }//init queue with size

    public Queue (){//사이즈가 정해지지 않았을 경우
        this.front = -1;
        this.rear = -1;
        this.size = 0;
        this.queue = new Object[100];//디폴트 큐 크기
    }

    @Override
    public boolean isFull() {
        return (this.rear == this.size-1);
    } // 끝 인덱스의 값이 사이즈와 동일하다면 가득 찬 것임

    @Override
    public synchronized boolean isEmpty() {
        if(front==rear) // 시작 인덱스와 끝 인덱스가 같으면 빈 것, -1로 초기화
        {
            front = -1;
            rear = -1;
        }
        return (this.front ==this.rear); // 같은지 여부를 boolean으로 return
    }

    @Override
     public synchronized void enqueue(T t) {//Enqueue
        if(isFull()){
            //System.out.println("Queue is Full"); // 큐가 가득 차 있으면 enqueue하지 않고 return
            return;
        }
        //System.out.println("enqueue: "+ t); // enqueue t
        rear = (rear+1) % this.size; //끝 인덱스 변경, Circular Queue 활용
        queue[rear] = t; // IN
    }

    @Override
     public synchronized T dequeue() {// Dequeue
        if(isEmpty()){
            //System.out.println("Queue is Empty");
            return null;//큐가 비어있다면, dequeue하지 않고 null을 반환
        }
        front = (front+1)%this.size;//시작 인덱스 변경, Circular Queue 활용
        //System.out.println("dequeue: " + this.queue[front]);
        return (T)queue[front];//큐 값을 반환
    }

    @Override
    public synchronized T peek() {//peek
        if(isEmpty()){
            //System.out.println("Queue is Empty");
            return null;
        }
        return (T)queue[front+1];
    }

    @Override
    public int size() {
        return Math.abs((rear+1)-(front+1));
    }//큐 사이즈

    @Override
    public void clear() {//큐 비우기
        if(isEmpty()){
            //System.out.println("Queue is Empty");
        }else{
            front = -1;
            rear = -1;
            queue = new Object[this.size];
            //System.out.println("Clear the Queue");
        }
    }
}

public class Supermarket {
    public static long serveCustomer(int[] customers, int n) {

        // TODO: implement intialization here
        //n == number of cashier
        //System.out.println("Customer: " + customers.length + ", Cashier: " + n);
        if(customers.length==0)//점원이 없다면 0반환
            return 0L;

        Queue<Integer> intQue = new src.Queue<Integer>(customers.length);//작업 큐 (Integer)
        Cashier[] cashiers = new Cashier[n];//number of cashiers
        Queue<CashierTask> cshQue = new src.Queue<CashierTask>(100);//스레드를 저장하는 큐 선언  *큐 크기는 여유롭게 50.. 사실 잘 모르겠음

        for (int i= 0;i<n;i++){
            cashiers[i] = new Cashier();
        }//점원 인스턴스 초기화

        for(int i = 0;i<customers.length;i++) {
            intQue.enqueue(customers[i]);
        }//enqueue the data



        // ===================================================
        long start = System.currentTimeMillis();

        // TODO: implement processing tasks here

        //rewrite the code
        while (!intQue.isEmpty())//큐가 비어있지 않다면 반복
        {
            for(int i = 0; i<cashiers.length;i++)//cashiers.length 명의 점원에게 일을 맡김
            {
                if(!cashiers[i].isWorking()){//i번째 점원이 일을 하고 있지 않다면
                    CashierTask chTask = new CashierTask(cashiers[i],intQue.dequeue());//새로운 스레드를 만들고 i번 점원에게 queue에 담긴 task를 줌
                    cashiers[i].setWork(true);//work status update
                    chTask.start();//점원 작업 시작
                    cshQue.enqueue(chTask);//스레드 큐 enqueue

                    break;//0번 점원부터 다시 확인
                }
            }
        }
        while (!cshQue.isEmpty()){//만일 스레드 큐가 비어있지 않다면
            Thread thread = cshQue.dequeue(); // dequeue해서 새로운 스레드에 저장
            try {
                thread.join();//join enqueued chTask
                //.out.println("#############thread join##########");
            }catch (InterruptedException e){
                System.out.println(e);
            }
        }

        int elapsedTime = Long.valueOf((System.currentTimeMillis() - start) / 100).intValue();
        // ===================================================

        return elapsedTime;
    }

}
