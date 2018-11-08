package pemwa.com.javadevsnairobi;

import android.os.Parcel;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import pemwa.com.javadevsnairobi.model.UserDetails;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class UserParcelabeTest {

    @Test
    public void testParcelable() {
        UserDetails user = new UserDetails();
        Parcel parcel = Parcel.obtain();
        user.setFollowers("20");
        user.setLocation("Nairobi");
        user.setOrganization("Andela");
        user.setRepos("102");
        user.writeToParcel(parcel, user.describeContents());
        parcel.setDataPosition(0);

        UserDetails getFromParcel = UserDetails.CREATOR.createFromParcel(parcel);

        assertThat(getFromParcel.getFollowers(), is("20"));
        assertThat(getFromParcel.getLocation(), is("Nairobi"));
        assertThat(getFromParcel.getOrganization(), is("Andela"));
        assertThat(getFromParcel.getRepos(), is("102"));
    }
}
