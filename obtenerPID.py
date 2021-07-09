import os
import threading

def gastar_cpu():
    while True:
        pass

# obtener el PID
print(" El Pid es :", os.getpid())
print("Hilos activos : ", threading.activeCount())