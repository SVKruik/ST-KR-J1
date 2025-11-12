import numpy as np
import matplotlib.pyplot as plt
from matplotlib.lines import Line2D
from sklearn import (svm, metrics)
from sklearn.metrics import confusion_matrix, ConfusionMatrixDisplay
from sklearn.ensemble import RandomForestClassifier

def load_wdbc_data(filename):
    class WDBCData:
        data          = [] # Shape: (569, 30)
        target        = [] # Shape: (569, )
        target_names  = ['malignant', 'benign']
        feature_names = ['mean radius', 'mean texture', 'mean perimeter', 'mean area', 'mean smoothness', 'mean compactness', 'mean concavity', 'mean concave points', 'mean symmetry', 'mean fractal dimension',
                         'radius error', 'texture error', 'perimeter error', 'area error', 'smoothness error', 'compactness error', 'concavity error', 'concave points error', 'symmetry error', 'fractal dimension error',
                         'worst radius', 'worst texture', 'worst perimeter', 'worst area', 'worst smoothness', 'worst compactness', 'worst concavity', 'worst concave points', 'worst symmetry', 'worst fractal dimension']
    wdbc = WDBCData()
    with open(filename) as f:
        for line in f.readlines():
            items = line.split(',')
            wdbc.target.append(0 if items[1] == 'M' else 1)
            wdbc.data.append([float(x) for x in items[2:]])
        wdbc.data = np.array(wdbc.data)
    return wdbc

# Extracted the scatter plot for less duplication
def plot_accuracy_and_scatter(model, X, y, feature_names, target_names, title):
    model.fit(X, y)
    predict = model.predict(X)
    accuracy = metrics.balanced_accuracy_score(y, predict)
    cmap = np.array([(1, 0, 0), (0, 1, 0)])
    clabel = [Line2D([0], [0], marker='o', lw=0, label=target_names[i], color=cmap[i]) for i in range(len(cmap))]
    for (x, y_) in [(0, 1)]:
        plt.figure()
        plt.title(f'{title} (Accuracy: {accuracy:.3f})')
        plt.scatter(X[:,x], X[:,y_], c=cmap[y], edgecolors=cmap[predict])
        plt.xlabel(feature_names[x])
        plt.ylabel(feature_names[y_])
        plt.legend(handles=clabel, framealpha=0.5)
    return predict, accuracy

if __name__ == '__main__':
    # Load a dataset
    wdbc = load_wdbc_data('data/wdbc.data')

    # PROFESSOR SOLUTION
    model_prof = svm.SVC()
    predict_prof, accuracy_prof = plot_accuracy_and_scatter(
        model_prof, wdbc.data, wdbc.target, wdbc.feature_names, wdbc.target_names, "Professor Edition"
    )

    # STUDENT SOLUTION
    model_stud = RandomForestClassifier(n_estimators=200, random_state=42, class_weight='balanced')
    predict_stud, accuracy_stud = plot_accuracy_and_scatter(
        model_stud, wdbc.data, wdbc.target, wdbc.feature_names, wdbc.target_names, "Student Edition"
    )

    # Confusion matrix for Student Edition
    cm = confusion_matrix(wdbc.target, predict_stud)
    disp = ConfusionMatrixDisplay(confusion_matrix=cm, display_labels=['malignant','benign'])
    fig, ax = plt.subplots(figsize=(5,5))
    disp.plot(ax=ax, cmap='viridis', colorbar=True)
    plt.show()