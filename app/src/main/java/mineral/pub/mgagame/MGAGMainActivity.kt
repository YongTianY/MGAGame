package mineral.pub.mgagame

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_mgagmain.*
import org.eclipse.paho.client.mqttv3.*
import java.util.*

class MGAGMainActivity() : AppCompatActivity() , MqttCallback, Parcelable {
    override fun messageArrived(topic: String?, message: MqttMessage?) {
        Log.d("RET","" + topic + " , " + message.toString())
    }

    override fun connectionLost(cause: Throwable?) {
        Log.d("RET","connection Lost",cause)
    }

    override fun deliveryComplete(token: IMqttDeliveryToken?) {
    }

    var mClient : MqttClient? = null

    constructor(parcel: Parcel) : this() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mgagmain)
        setSupportActionBar(toolbar)
        System.setProperty("user.dir","/sdcard")
        mClient = MqttClient("tcp://192.168.5.106:1883", "testid");
        mClient!!.setCallback(this)
        mClient!!.connect()

        mClient!!.subscribe("Client.resp")
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_mgagmain, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MGAGMainActivity> {
        override fun createFromParcel(parcel: Parcel): MGAGMainActivity {
            return MGAGMainActivity(parcel)
        }

        override fun newArray(size: Int): Array<MGAGMainActivity?> {
            return arrayOfNulls(size)
        }
    }
}
