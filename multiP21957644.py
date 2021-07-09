import threading
import multiprocessing as mp
import os

if __name__ =="__main__":
    pass
def gastar_cpu():
    while True:
        pass

#Info del proceso
print('ID del proceso: ', os.getpid())
print('Hilos activos: ', threading.active_count())
for hilo in threading.enumerate():
    print("Hilo -->"+str(hilo))

for h in range(5):
    #threading.Thread(target=gastar_cpu).start()
    mp.Process(target=gastar_cpu()).start()

#Info del proceso
print('\nID del proceso: ', os.getpid())
print('\nHilos activos: ', threading.active_count())
for h in threading.enumerate():
    print("Hilo -->"+str(h))