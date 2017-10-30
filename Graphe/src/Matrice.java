

/**
 * Represente une matrice [i,j] symetrique trianglulaire supperieur
 * (i,j)=(j,i)
 * @author 
 */
public class Matrice<T extends Object> {

	private Object matrice[][];
	public final int length;
	public Matrice(final Ville[] villes) {
		this(villes, null);
	}
	public Matrice(final Ville[] villes, final T defaultValue) {
		length = villes.length;
		matrice = new Object[length][length];
		for (int i = 0; i < length; i++)
			matrice[i] = new Object[length-i];
		
		resetAll(defaultValue);
	}
	public  void resetAll(final T defaultValue){
		for (int i = 0; i < length; i++) {
			for (int j = i; j < length; j++) {
				matrice[i][j - i] = defaultValue;
			}
		}	
	}
	public void set(final int i,final int j, final T value){
		final int i1 = Math.min(i, j);
		final int j1 = Math.max(i, j);
		matrice[i1][j1 - i1] = value;
	}
	
	@SuppressWarnings("unchecked")
	public T get(final int i, final int j){
		final int i1 = Math.min(i, j);
		final int j1 = Math.max(i, j);
		if(matrice[i1][j1 - i1] == null)
			return null;
		return (T)matrice[i1][j1 - i1];
	}
}
