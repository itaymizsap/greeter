package org.example.daytime;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class DayTimeResolverTests {

	private static final DayTimeResolver dayTimeResolver = new DayTimeResolver();

	@Test
	public void testDayTimeResolutionForMorningHour11AM() {
		Assert.assertThat(dayTimeResolver.resolve((short) 11), is("morning"));
	}

	@Test
	public void testDayTimeResolutionForMorningHour5AM() {
		Assert.assertThat(dayTimeResolver.resolve((short) 5), is("morning"));
	}

	@Test
	public void testDayTimeResolutionForAfternoonHour12PM() {
		Assert.assertThat(dayTimeResolver.resolve((short) 12), is("afternoon"));
	}

	@Test
	public void testDayTimeResolutionForNightHour10PM() {
		Assert.assertThat(dayTimeResolver.resolve((short) 22), is("night"));
	}

	@Test
	public void testDayTimeResolutionForNightHour9PM() {
		Assert.assertThat(dayTimeResolver.resolve((short) 21), is("night"));
	}

	@Test
	public void testDayTimeResolutionForNightHour2AM() {
		Assert.assertThat(dayTimeResolver.resolve((short) 2), is("night"));
	}
}
