package com.PojoData;

public class TestDataStore {

	private static Long petId;
	private static String petName;

	public static Long getPetId() {
		return petId;
	}

	public static void setPetId(Long Id) {
		petId = Id;
	}

	public static String getPetName() {
		return petName;
	}

	public static void setPetName(String Name) {
		petName = Name;

	}
}
