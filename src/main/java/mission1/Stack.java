package mission1;

import java.util.EmptyStackException;

public interface Stack <E>{
	
		/**
		 * Test si la Stack est vide
		 * @return true si la stack et vide false aussi non
		 */
	    public boolean empty();

	    /**
	     * Regarde l'élément au sommet de la Stack sans le retirer de la pile
	     * @return les données de l'élément du sommet de la Stack
	     * @throws EmptyStackException
	     */
	    public E peek() throws EmptyStackException;

	    /**
	     * Retire le premier élément de la Stack
	     * @return les données du premier élément de la Stack
	     * @throws EmptyStackException
	     */
	    public E pop() throws EmptyStackException;

	    /**
	     * Ajoute un élément au sommet de la Stack
	     * @param item élément à ajouter à la Stack
	     * @return l'élément ajouté à la Stack
	     */
	    public E push(E item);
}
