package cayuse.exercise;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cayuse.exercise.service.data.ZipCodeMetaData;

public class LocationTest {

	@Test
	public void testGetFormattedMetaData() {
		ZipCodeMetaData metaData = new ZipCodeMetaData();
		metaData.setCity("Portland");
		metaData.setTemperature(101);

		assertEquals(
				"At the location Portland, the temperature is 101, the timezone is $TIMEZONE, and the elevation is $ELEVATION \n",
				Location.getFormattedMetaData(metaData));

		metaData.setTemperature(45);
		assertEquals(
				"At the location Portland, the temperature is 45, the timezone is $TIMEZONE, and the elevation is $ELEVATION \n",
				Location.getFormattedMetaData(metaData));

		metaData.setTemperature(2);
		assertEquals(
				"At the location Portland, the temperature is 2, the timezone is $TIMEZONE, and the elevation is $ELEVATION \n",
				Location.getFormattedMetaData(metaData));

		metaData.setTemperature(0);
		assertEquals(
				"At the location Portland, the temperature is 0, the timezone is $TIMEZONE, and the elevation is $ELEVATION \n",
				Location.getFormattedMetaData(metaData));

		metaData.setTemperature(-3);
		assertEquals(
				"At the location Portland, the temperature is -3, the timezone is $TIMEZONE, and the elevation is $ELEVATION \n",
				Location.getFormattedMetaData(metaData));

		metaData.setTemperature(-55);
		assertEquals(
				"At the location Portland, the temperature is -55, the timezone is $TIMEZONE, and the elevation is $ELEVATION \n",
				Location.getFormattedMetaData(metaData));

	}

}
