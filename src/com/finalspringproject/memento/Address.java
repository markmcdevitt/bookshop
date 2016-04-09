package com.finalspringproject.memento;

public class Address {

	private String address;

	public void setAddress(String address) {
		System.out.println("setting address to: " + address);
		this.address = address;
	}
	

	public Memento saveToMemento() {
		System.out.println("Saving address to Memento");
		return new Memento(address);
	}
	
	public String restoreFromMemento(Memento memento) {
		System.out.println("Address restored from Memento: " + address);
		return address = memento.getSavedAddress();
		
	}
	
	public static class Memento {
		private final String address;

		public Memento(String addressToSave) {
			address = addressToSave;
		}

		public String getSavedAddress() {
			return address;
		}

		@Override
		public String toString() {
			return address;
		}
		
	}
}
