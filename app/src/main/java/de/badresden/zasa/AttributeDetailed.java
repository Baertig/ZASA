package de.badresden.zasa;

/**
 * Class that holds the Description of a Field togeather with its description.
 * It is used primarly for printing. At least that is my intention.
 */
public class AttributeDetailed {
	private String description;
	private Object Value;

	public AttributeDetailed(String description, Object value) {
		this.description = description;
		Value = value;
	}

	public String getDescription() {
		return description;
	}

	public Object getValue() {
		return Value;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setValue(Object value) {
		Value = value;
	}
}
