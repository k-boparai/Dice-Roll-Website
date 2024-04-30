package ca.sheridancollege.bopkaran.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Roll {
    private int id;
    public int rollNumber;
    public int rollValue;
}