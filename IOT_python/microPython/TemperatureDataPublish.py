import network
import dht
import machine
import time
from umqttsimple import MQTTClient

#i have taken the following piece of code from:https://docs.micropython.org/en/latest/esp32/quickref.html

wlan = network.WLAN(network.STA_IF) # create station interface
wlan.active(True)       # activate the interface
wlan.connect('Rechnernetze', 'rnFIW625') # connect to an AP
print(wlan.ifconfig())

while wlan.isconnected() == False:
  pass

print(wlan.ifconfig())
d = dht.DHT22(machine.Pin(15))
d.measure()
d.temperature() # eg. 23.6 (°C)
print("temp is:",d.temperature())
d.humidity()    # eg. 41.3 (% RH)
print("humidity is:",d.humidity())


client=MQTTClient("soorya","broker.hivemq.com")
client.connect()

while True:
    d.measure()
    client.publish("M_IoT/soorya/temp",str(d.temperature()))
    client.publish("M_IoT/soorya/humi",str(d.humidity()))
    time.sleep(10)
