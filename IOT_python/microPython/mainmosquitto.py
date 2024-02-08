import network
import dht
import machine
import time
import ubinascii
from umqttsimple import MQTTClient

# Wifi Parameters
WIFI_USERNAME = "Rechnernetze"
WIFI_Password = "rnFIW625"

# MQTT Server Parameters
MQTT_CLIENT_ID = ubinascii.hexlify(machine.unique_id())
MQTT_BROKER = "10.10.3.33"
MQTT_USER = "test1"
MQTT_PASSWORD = "12345"
MQTT_PORT = 1888

# Set the last will message
LAST_WILL_TOPIC = "M_IoT/soorya/lastwill"
LAST_WILL_MESSAGE = "The broker disconnected unexpectedly"
LAST_WILL_QOS = 1
LAST_WILL_RETAIN = True


def restart_and_reconnect():
    print('Failed to connect to MQTT broker. Reconnecting...')
    time.sleep(10)
    machine.reset()

def connect_mqtt():
    try:
        client = MQTTClient(MQTT_CLIENT_ID, MQTT_BROKER, user=MQTT_USER, password=MQTT_PASSWORD, port=MQTT_PORT, keepalive=60)        
        client.set_last_will(LAST_WILL_TOPIC, LAST_WILL_MESSAGE, LAST_WILL_QOS, LAST_WILL_RETAIN)
        client.connect()        
        return client
    except OSError:
        print("Error connecting to MQTT broker")
        raise

def connect_to_wifi(ssid, password):
    wifi.active(True)
    wifi.connect(ssid, password)

    while not wifi.isconnected():
        print("Connecting to Wi-Fi...")
        time.sleep(1)

    print("Connected to Wi-Fi")
    print("IP Address:", wifi.ifconfig()[0])

def main():
    print("main!")
    #connecting to wifi
    wifi = network.WLAN(network.STA_IF)
    connect_to_wifi(WIFI_USERNAME, WIFI_Password)

    # Allow only when wifi connected
    while not wifi.isconnected():
        pass
   
   #sensor initialiation to pinno
    sensor = dht.DHT22(machine.Pin(2))

    try:
        #connecting to broker
        client = connect_mqtt()
        print("Connected!")
        while True:                   
            print("inside Mosquitto broker!")
            sensor.measure()
            temp, hum = sensor.temperature(), sensor.humidity()
            print(temp)
            print(hum)
            #checking the reading value
            if isinstance(temp, (float, int)) and isinstance(hum, (float, int)):
                client.publish("M_IoT/soorya/temp", str(temp))
                client.publish("M_IoT/soorya/humi", str(hum))
                time.sleep(10)
            else:
                print("Invalid reading")
    except OSError:
        restart_and_reconnect()

if __name__ == "__main__":
    wifi = network.WLAN(network.STA_IF)
    main()

