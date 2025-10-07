import matplotlib.pyplot as plt

def read_data(filename):
    data = []
    with open(filename, 'r') as f:
        for line in f.readlines():
            if not line.startswith('#'): # If 'line' is not a header
                data.append([int(word) for word in line.split(',')])
    return data

if __name__ == '__main__':
    # Load score data
    class_kr = read_data('data/class_score_kr.csv')
    class_en = read_data('data/class_score_en.csv')

    # Prepare midterm, final, and total scores
    midterm_kr, final_kr = zip(*class_kr)
    midterm_en, final_en = zip(*class_en)
    total_kr = [40/125*midterm + 60/100*final for (midterm, final) in class_kr]
    total_en = [40/125*midterm + 60/100*final for (midterm, final) in class_en]

    # Plot midterm/final scores as points
    plt.figure(figsize=(10, 5))
    plt.scatter(midterm_kr, final_kr, color='red', label='Korean', marker='o')
    plt.scatter(midterm_en, final_en, color='blue', label='English', marker='+')
    plt.xlabel('Midterm scores')
    plt.ylabel('Final scores')
    plt.legend()
    plt.grid(True)
    plt.xlim(0, 125)
    plt.ylim(0, 100)

    # Plot total scores as a histogram
    plt.figure(figsize=(10, 5))
    plt.hist(total_kr, bins=range(0, 105, 5), color='red', alpha=0.7, label='Korean')
    plt.hist(total_en, bins=range(0, 105, 5), color='mediumpurple', alpha=0.7, label='English')
    plt.xlabel('Total scores')
    plt.ylabel('The number of students')
    plt.legend()
    plt.xlim(0, 100)

    plt.show()