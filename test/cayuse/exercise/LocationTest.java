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
		metaData.setTimeZone("Pacific Standard Time");
		metaData.setElevation(10000);

		assertEquals(
				"At the location Portland, the temperature is 101, the timezone is Pacific Standard Time, and the elevation is 10,000\n",
				Location.getFormattedMetaData(metaData));

		metaData.setTemperature(45);
		metaData.setElevation(25);
		assertEquals(
				"At the location Portland, the temperature is 45, the timezone is Pacific Standard Time, and the elevation is 25\n",
				Location.getFormattedMetaData(metaData));

		metaData.setTemperature(2);
		assertEquals(
				"At the location Portland, the temperature is 2, the timezone is Pacific Standard Time, and the elevation is 25\n",
				Location.getFormattedMetaData(metaData));

		metaData.setTemperature(0);
		metaData.setElevation(0);
		assertEquals(
				"At the location Portland, the temperature is 0, the timezone is Pacific Standard Time, and the elevation is 0\n",
				Location.getFormattedMetaData(metaData));

		metaData.setTemperature(-3);
		metaData.setElevation(-44);
		assertEquals(
				"At the location Portland, the temperature is -3, the timezone is Pacific Standard Time, and the elevation is -44\n",
				Location.getFormattedMetaData(metaData));

		metaData.setTemperature(-55);
		assertEquals(
				"At the location Portland, the temperature is -55, the timezone is Pacific Standard Time, and the elevation is -44\n",
				Location.getFormattedMetaData(metaData));
		
		metaData.setTemperature(8.5);
		metaData.setElevation(12.6);
		assertEquals(
				"At the location Portland, the temperature is 9, the timezone is Pacific Standard Time, and the elevation is 13\n",
				Location.getFormattedMetaData(metaData));
		
		metaData.setTemperature(8.4);
		metaData.setElevation(12.5);
		assertEquals(
				"At the location Portland, the temperature is 8, the timezone is Pacific Standard Time, and the elevation is 12\n",
				Location.getFormattedMetaData(metaData));

	}

}