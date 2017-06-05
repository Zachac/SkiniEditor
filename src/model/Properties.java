package model;

/**
 * A class to signify that an object contains properties from skin.ini file. A class that implements this interface 
 * needs to ensure that: 
 *      - any method starting with "set" should have only one parameter and should set the given value identified after
 *        the word set.
 *      - the toString of the class should output a valid part of a skin.ini file for it's properties.
 *
 * @author Zachary Chandler
 */
public interface Properties { }
