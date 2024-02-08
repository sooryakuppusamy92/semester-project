import network
import dht
import machine
import time
import ubinascii
from umqttsimple import MQTTClient

# Wifi Parameters
WIFI_USERNAME = "Rechnernetze"
WIFI_Password = "rnFIW625"

#i have taken the following piece of code from:https://docs.micropython.org/en/latest/esp32/quickref.html
# MQTT Server Parameters
MQTT_CLIENT_ID = ubinascii.hexlify(machine.unique_id())
MQTT_BROKER    = "10.10.3.33"
MQTT_USER      = "test1"
MQTT_PASSWORD  = "12345"
MQTT_PORT     = 1888

def restart_and_reconnect():
  print('Failed to connect to MQTT broker. Reconnecting...')
  time.sleep(10)
  machine.reset() 
  
  
def reconnect():
    while not client.connect(clean_session=False):
        print("Reconnected to MQTT broker")

def connect_mqtt():
    client = MQTTClient(MQTT_CLIENT_ID, MQTT_BROKER, user=MQTT_USER, password=MQTT_PASSWORD,port=MQTT_PORT,keepalive=60)
# Set the last will message
    last_will_topic = "M_IoT/soorya/lastwill"
    last_will_message = "Client unexpectedly disconnected soorya"
    last_will_qos = 1
    last_will_retain = False
    client.set_last_will(last_will_topic, last_will_message, last_will_qos, last_will_retain)
    client.connect()
    return client

#connect to wifi
def connect_to_wifi(ssid, password):
    
    wifi.active(True)
    wifi.connect(ssid, password)
 
    while not wifi.isconnected():
        print("Connecting to Wi-Fi...")
        time.sleep(1)
 
    print("Connected to Wi-Fi")
    print("IP Address:", wifi.ifconfig()[0])


print("i am coming again!")
    
wifi = network.WLAN(network.STA_IF)
connect_to_wifi(WIFI_USERNAME, WIFI_Password)
#Allow only when wifi connected
while wifi.isconnected() == False:
  pass

sensor = dht.DHT22(machine.Pin(2))

##client=MQTTClient("soorya","broker.hivemq.com")
##client.connect()
try:
    client=connect_mqtt()
    print("Connected!")
    while True:
        print("here mosquitto!")
        sensor.measure()
        temp = sensor.temperature()
        print(temp)
        hum = sensor.humidity()
        print(hum)
        if (isinstance(temp, float) and isinstance(hum, float)) or (isinstance(temp, int) and isinstance(hum, int)):
            client.publish("M_IoT/soorya/temp",str(sensor.temperature()))
            client.publish("M_IoT/soorya/humi",str(sensor.humidity()))
            time.sleep(10)
        else:
            print("invalid reading")
except OSError as e:
  restart_and_reconnect() 

