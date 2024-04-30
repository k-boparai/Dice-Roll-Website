package ca.sheridancollege.bopkaran.beans;

import lombok.*;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Dice {

	private int id;
	@NonNull
	public int diceNumber;
	@NonNull
	public int diceFaces;
	@NonNull
	public int diceTotal;
	public ArrayList<Integer> results;
	
}
