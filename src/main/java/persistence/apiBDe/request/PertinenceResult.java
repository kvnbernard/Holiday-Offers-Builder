package persistence.apiBDe.request;

/**
 * Association with an element and the pertience score
 * 
 * @author Aldric Vitali Silvestre
 */
public class PertinenceResult{
	
	// --- Variables ---
	
	private float score;
	private String name;

	
	// --- Methods ---
	
	public PertinenceResult(float score, String name) {
		this.score = score;
		this.name = name;
	}


	public float getScore() {
		return score;
	}


	public void setScore(float score) {
		this.score = score;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
