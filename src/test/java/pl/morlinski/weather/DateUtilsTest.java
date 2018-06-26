package pl.morlinski.weather;


import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.Test;

public class DateUtilsTest {

    @Test
    public void testConvertTimestampToLocalDataTime() {
        //given
        LocalDateTime now = LocalDateTime.now();
        //when
        LocalDateTime time = DateUtils.convertTimestampToLocalDataTime(System.currentTimeMillis()/1000);
        //then
        assertThat(time).isNotNull();
        assertThat(time.getYear()).isEqualTo(now.getYear());
        assertThat(time.getMonth()).isEqualTo(now.getMonth());
        assertThat(time.getDayOfMonth()).isEqualTo(now.getDayOfMonth());
        assertThat(time.getHour()).isEqualTo(now.getHour());
    }
    

    @Test
    public void testTimeIsBetween() {
        //given
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime after = LocalDateTime.now().minusDays(1);
        LocalDateTime before = LocalDateTime.now().plusDays(1);
        //when
        boolean result = DateUtils.timeIsBetween(now, after, before);
        //then
        assertThat(result).isEqualTo(true);
    }
    
    @Test
    public void testTimeIsBetweenBefore() {
        //given
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime before = LocalDateTime.now().plusDays(1);
        //when
        boolean result = DateUtils.timeIsBetween(now, before, before);
        //then
        assertThat(result).isEqualTo(false);
    }
    
    @Test
    public void testTimeIsBetweenAfter() {
        //given
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime after = LocalDateTime.now().minusDays(1);
        //when
        boolean result = DateUtils.timeIsBetween(now, after, after);
        //then
        assertThat(result).isEqualTo(false);
    }

}
