import multiprocessing as mp
import math
import random

def seq_mat_m(A,B):
    #obtener las fils y cols de cada matriz
    n_fil_A = len(A)
    n_col_A = len(A[0])
    n_fil_B = len(B)
    n_col_B = len(B[0])
    
    if n_col_A!=n_fil_B:
        #funcion equvalente try catch de java
        raise Exception('Matriz no compatible')

    C = [[0]*n_col_B for i in range(n_fil_A)]#usamos list comprehension
    #forma de hacer la superior alternativamente
    '''for i in range(n_fil_A):
        for j in range(n_col_B):
            C[i][j]=0'''
    
    for i in range(n_fil_A):
        for j in range(n_col_B):
            for k in range(n_col_B):
                C[i][j] += A[i][k] * B[k][j]
    return C


def par_mat_m(A,B):
    #obtener las fils y cols de cada matriz
    n_fil_A = len(A)
    n_col_A = len(A[0])
    n_fil_B = len(B)
    n_col_B = len(B[0])
    
    if n_col_A!=n_fil_B:
        #funcion equvalente try catch de java
        raise Exception('Matriz no compatible')
    #averiguamos el numero de cores de nuestro PC
    n_cores = mp.cpu_count()
    size = math.ceil(n_fil_A/n_cores)

    #creo la memoria compartida donde se almacenaran los resultados
    C_1D = mp.RawArray('d', n_fil_A*n_col_B) #con 'd' definimos el tipo de datos guardados por RawArray

    #Creo array de cores para poder lanzar en paralelo
    core = []

    for w in range(n_cores):
        i_fila_C = min(w*size, n_fil_A)
        f_fila_C = min((w+1)*size, n_fil_A)
        core.append(mp.Process(target= par_worker, args=(A,B, C_1D, i_fila_C, f_fila_C)))

    for w in core:
        w.start()
    for w in core:
        w.join()

    #convierto el array unidimensional en 2D
    C_2D = [[0] * n_col_B for i in range(n_fil_A)]

    for i in range(n_fil_A):
        for j in range(n_col_B):
            C_2D[i][j] = C_1D[i*n_col_B+j]
    return C_2D
    

def par_worker(A,B, C_1D, i_fila_C, f_fila_C):#reparte el trabajo entre cada uno de los cores
    for i in range(i_fila_C, f_fila_C): #subset filas en A
        for j in range(len(B[0])): #n_col_B
            for k in range(len(A[0])):#n_cols_b, tambien las n_fil_B
                C_1D[i*len(B[0]) + j] += A[i][k] * B[k][j]

if __name__ == '__main__':
    A = [[random.random() for i in range(200)] for j in range(200)]
    B = [[random.random() for i in range(200)] for j in range(200)]

    sec_res = seq_mat_m(A,B)
    par_res = par_mat_m(A,B)
