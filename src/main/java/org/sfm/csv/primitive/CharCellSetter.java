package org.sfm.csv.primitive;

import org.sfm.csv.CellSetter;
import org.sfm.csv.cell.IntegerCellValueReader;
import org.sfm.reflect.primitive.CharacterSetter;

public class CharCellSetter<T> implements CellSetter<T> {

	private final CharacterSetter<T> setter;
	
	public CharCellSetter(CharacterSetter<T> setter) {
		this.setter = setter;
	}
	
	@Override
	public void set(T target, byte[] bytes, int offset, int length)
			throws Exception {
		setter.setCharacter(target, (char) IntegerCellValueReader.parseInt(bytes, offset, length));
	}
}
