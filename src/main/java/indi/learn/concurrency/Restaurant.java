package indi.learn.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Meal {
	private final int id;
	
	public Meal(int idn) {
		this.id = idn;
	}
	
	public String toString() {
		return "Meal[" + id + "]"; 
	}
}

class WaitPerson implements Runnable {
	private Restaurant restaurant;
	
	public WaitPerson(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	public void run() {
		try {
			while(!Thread.currentThread().isInterrupted()) {
				synchronized(this) {
					while(restaurant.meal == null) {
						wait();
					}
				}
				System.out.println("Waitperson got " + restaurant.meal);
				synchronized(restaurant.chef) {
					restaurant.meal = null;
					restaurant.chef.notifyAll();
				}
			}
		} catch(InterruptedException e) {
			System.out.println("WaitPerson interrupted");
		}
	}
}

class Chef implements Runnable {
	private Restaurant restaurant;
	
	private int count = 0;
	
	public Chef(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	public void run() {
		try {
			while(!Thread.currentThread().isInterrupted()) {
				synchronized(this) {
					while(restaurant.meal != null) {
						wait();
					}
				}
				
				if(++count == 10) {
					System.out.println("Out of food, closing");
					restaurant.service.shutdownNow();
				}
				System.out.println("Order up");
				
				synchronized(restaurant.waitPerson) {
					restaurant.meal = new Meal(count);
					restaurant.waitPerson.notifyAll();
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}
		} catch(InterruptedException e) {
			System.out.println("Chef interrupted");
		}
		
	}
}

public class Restaurant {
	Meal meal;
	Chef chef;
	WaitPerson waitPerson;
	ExecutorService service;
	
	public Restaurant() {
		chef = new Chef(this);
		waitPerson = new WaitPerson(this);
		service = Executors.newCachedThreadPool();
		service.execute(chef);
		service.execute(waitPerson);
	}

	public static void main(String[] args) {
		new Restaurant();
	}

}
