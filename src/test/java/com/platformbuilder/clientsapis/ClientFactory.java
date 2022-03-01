package com.platformbuilder.clientsapis;

import java.util.Random;
import java.util.UUID;

import com.platformbuilder.clientsapis.dtos.ClientDTO;
import com.platformbuilder.clientsapis.entities.Client;

public class ClientFactory {
	
	private static String[] names = new String[] {
			"Jack", "Martin", "Steve", "Richard", "Lucas", "Albert", "Bob", "Ashley", "Andrea", "Nestor"
	};

	public static Client getClient() {
		return Client.builder()
				.age(getRandom(1, 121))
				.clientId(UUID.randomUUID().toString())
				.name(getName(getRandom(0, 9)))
				.build();
	}
	
	public static ClientDTO getClientDTO() {
		return ClientDTO.builder()
				.age(getRandom(1, 121))
				.clientId(UUID.randomUUID().toString())
				.name(getName(getRandom(0, 9)))
				.build();
	}
	
	private static int getRandom(int min, int max) {
	    Random random = new Random();
	    return random.nextInt(max - min) + min;
	}
	
	private static String getName(int position) {
		
		return names[position];
	}
	
}
