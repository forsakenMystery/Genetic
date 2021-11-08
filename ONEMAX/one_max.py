from sys import maxsize
from random import randint, random, sample
from statistics import mean
import time
import matplotlib.pyplot as plt
import numpy as np

CHANCE_OF_CROSSOVER = 0.6
NUM_MOST_FIT = 2
CONVERGENCE = 30
MAX_POP_SIZE = 81920
NUM_RUNS = 50


def evolve_population(population, population_size):
    # print("evolution")
    i = 0
    ti = time.time()
    while i < NUM_RUNS:
        # print("**********************************************")
        # input()
        # print(i+1, "/", NUM_RUNS)
        children = get_children(population, population_size)

        next_generation = get_next_generation(population, children)

        # print("next generation:", next_generation)
        population = next_generation

        if found_global_optimum(population) is 1:
            break

        i += 1
    t = time.time()

    return population, t-ti


def get_new_population_size(lower_bound, upper_bound):
    population_size = midpoint(lower_bound, upper_bound)
    if population_size % 2 == 1:
        population_size -= 1
    return population_size


def midpoint(p1, p2):
    return int((p1 + p2) / 2)


def get_children(population, population_size):
    children = []
    num_pairs_of_children = (population_size - NUM_MOST_FIT) / 2
    for _ in range(int(num_pairs_of_children)):
        # Selection
        parents = get_parents(population)

        # Recombination
        two_children = reproduce(parents)
        children += two_children

    return children


def get_next_generation(population, children):
    most_fit = get_most_fit_individuals(population)
    next_generation = children + most_fit
    return next_generation


def found_global_optimum(population):
    fitnesses = get_fitnesses(population)
    if max(fitnesses) == len(population[0]):
        return 1
    else:
        return 0


def compare_populations(p1, p2):
    p1_fitnesses = get_fitnesses(p1)
    p2_fitnesses = get_fitnesses(p2)
    if mean(p1_fitnesses) == mean(p2_fitnesses):
        return 1
    else:
        return 0

def get_most_fit_individuals(population):
    fitnesses = get_fitnesses(population)
    most_fit = []
    for i in range(NUM_MOST_FIT):
        the_fittest = max(fitnesses)
        fittest_idx = fitnesses.index(the_fittest)
        most_fit.append(population[fittest_idx])
    return most_fit


def reproduce(parents):
    if random() <= CHANCE_OF_CROSSOVER:
        children = uniform_crossover(parents)
    else:
        children = parents
    return [mutate(child) for child in children]


def mutate(child):
    length = len(child)
    child = list(child)
    chance_mutation = 1 / length
    for i in range(length):
        if random() > chance_mutation:
            continue
        if child[i] == '1':
            child[i] = '0'
        else:
            child[i] = '1'
    return ''.join(child)


def uniform_crossover(parents):
    string_len = len(parents[0])
    child1 = ''
    child2 = ''
    for i in range(string_len):
        parent_num = randint(0, 1)
        if parent_num == 1:
            other_parent_num = 0
        else:
            other_parent_num = 1
        child1 += parents[parent_num][i]
        child2 += parents[other_parent_num][i]
    return child1, child2


def get_parents(population):
    parents = []
    for _ in range(2):
        parent = get_parent(population)
        parents.append(parent)
    return parents


def get_parent(population):
    s = sample(population, 2)
    fitnesses = get_fitnesses(s)
    max_fitness = max(fitnesses)
    fittest_idx = fitnesses.index(max_fitness)
    return s[fittest_idx]


def get_fitnesses(population):
    return [fitness_function(individual) for individual in population]


def fitness_function(binary_string):
    return binary_string.count('1')


def get_population(population_size, string_size):
    population = []
    for _ in range(population_size):
        binary_string = get_random_binary_string(string_size)
        population.append(binary_string)
    return population


def get_random_binary_string(string_size):
    binary_str = ''
    for _ in range(string_size):
        binary_str += str(randint(0, 1))
    return binary_str


def main():
    population_size = 10
    string_size = 60
    lower_bound = 0
    upper_bound = maxsize
    has_succeeded = False
    mm = 0
    times = []
    while (upper_bound - lower_bound) > 2:
        found_global_optimum_cnt = 0

        print(mm + 1, "--> lower bound: ", lower_bound, ", upper bound", upper_bound, "population size: ",population_size)
        mm += 1
        for ll in range(CONVERGENCE):
            # print("======================")
            # print(ll, "/", CONVERGENCE)
            population = get_population(population_size, string_size)
            # print("population: ", population)
            population, time = evolve_population(population, population_size)

            found_global_optimum_cnt += found_global_optimum(population)
            if found_global_optimum(population) is 1:
                times.append({population_size: time})
            # print(found_global_optimum_cnt, " out of ", ll+1)
        if found_global_optimum_cnt >= 29:
            upper_bound = population_size
            print("it is optimal")
            # print(upper_bound)
            # input()
            population_size = get_new_population_size(lower_bound, upper_bound)
            has_succeeded = True
        else:
            lower_bound = population_size
            if has_succeeded:
                population_size = get_new_population_size(lower_bound, upper_bound)
            else:
                population_size *= 2

        if population_size >= MAX_POP_SIZE:
            print('Algorithm failed!')
            population_size /= 2
            break

    print('Population size: {}'.format(midpoint(lower_bound, upper_bound)))
    print(times)
    x = []
    y = []
    much = 0
    for t in times:
        # print(t)
        for key, value in t.items():
            if key in x:
                y[len(x)-1] = y[len(x)-1]*much + value
                much += 1
                y[len(x) - 1] /= much
            else:
                x.append(key)
                y.append(value)
                much = 1
    print("=============")
    print(x)
    print("*************")
    print(y)
    plt.scatter(x, np.log(y))
    plt.title("running the one max")
    plt.xlabel("population size")
    plt.ylabel("logarithm of time")
    plt.show()


if __name__ == '__main__':
    main()
