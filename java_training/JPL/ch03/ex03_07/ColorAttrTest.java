package ch03.ex03_07;

import java.lang.reflect.Field;

import junit.framework.TestCase;

import org.junit.Test;


public class ColorAttrTest extends TestCase{

	@Test
	//String�AScreenColor�Ƃ��ɓ����l�̏ꍇ
	public void testEquals1() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
		ColorAttr one = new ColorAttr("red");
		ColorAttr other = new ColorAttr("red");

		Class<ColorAttr> c = ColorAttr.class;
		Field field = c.getDeclaredField("myColor");
		field.setAccessible(true);
		field.set(one, new ScreenColorMock(true , 0));

		assertTrue(one.equals(other));
	}

	@Test
	//String�����l�łȂ��ꍇ
	public void testEquals2() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
		ColorAttr one = new ColorAttr("red");
		ColorAttr other = new ColorAttr("blue");

		Class<ColorAttr> c = ColorAttr.class;
		Field field = c.getDeclaredField("myColor");
		field.setAccessible(true);
		field.set(one, new ScreenColorMock(true , 0));

		assertFalse(one.equals(other));
	}

	@Test
	//ScreenColor�����l�łȂ��ꍇ
	public void testEquals3() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
		ColorAttr one = new ColorAttr("red");
		ColorAttr other = new ColorAttr("red");

		Class<ColorAttr> c = ColorAttr.class;
		Field field = c.getDeclaredField("myColor");
		field.setAccessible(true);
		field.set(one, new ScreenColorMock(false , 0));

		assertFalse(one.equals(other));
	}

	@Test
	//��r�Ώۂ̃I�u�W�F�N�g�̌^���Ⴄ�ꍇ
	public void testEquals4() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
		ColorAttr one = new ColorAttr("red");
		Attr other = new Attr("red");

		assertFalse(one.equals(other));
	}
}
