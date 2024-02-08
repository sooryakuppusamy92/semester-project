import dht
import machine
import network

wlan = network.WLAN(network.STA_IF) # create station interface
wlan.active(True)       # activate the interface
wlan.connect('Wokwi-GUEST', '') # connect to an AP

d = dht.DHT22(machine.Pin(4))
d.measure()
print('hello i start working...')
d.temperature() # eg. 23.6 (°C)
print(d.temperature())
d.humidity()    # eg. 41.3 (% RH)
print(d.humidity())



