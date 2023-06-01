#include <fstream>
#include <iostream>
#include <string>
#include <vector>
#include <map>
#include <stack>

using namespace std;

int main() {
  fstream file;
  file.open("nfa.txt", ios_base::in);

  vector<string> states; // store the states of the automata
  vector<string> alphabets; // store the transition bit 

  map<string, int> alphaIndex; // map the string alphabets to an integer to ue it with transition matrix
  map<string, int> stateIndex; // map the string states to an integer to use it with transition matrix

  vector<vector<vector<int>>> transitions; // 3D transition matrix
  string initialState; // store the initial state
  vector<bool> finalStates; // store the final states

  vector<string> inputs; // to take all thing in file in this vector

  //loop to parse the .txt file
  if (file.is_open()) {
    while (file.good()) {
      string s;
      file >> s;
      inputs.push_back(s);
    }
  }

  int index = 0; // pointer to input vector

  int temp = stoi(inputs[index]); // no of states
  int m = stoi(inputs[index]); // no of states
  index++;

  //filling the states in the states vector & stateIndex
  while (temp--) {
    string tempS = inputs[index];
    states.push_back(tempS);
    stateIndex[tempS] = (int)(states.size() - 1);
    index++;
  }
  temp = stoi(inputs[index]); // no of alphabet
  index++;
 // filling the alphabet vector & alphaIndex
  while (temp--) {
    string tempS = inputs[index];
    index++;
    alphabets.push_back(tempS);
    alphaIndex[tempS] = (int)(alphabets.size() - 1);
  }

  temp = stoi(inputs[index]);
  index++;
 // resizing  of transition  vector
  transitions.resize(states.size());
  for (auto &x : transitions) {
    x.resize(states.size());
  }
  // filling of the transition table
  while (temp--) {
    string cState = inputs[index];
    index++;
    string cAlpha = inputs[index];
    index++;
    int nums = stoi(inputs[index]);
    index++;

    while (nums--) {
      string curr = inputs[index];
      index++;
      transitions[stateIndex[cState]][stateIndex[curr]].push_back(alphaIndex[cAlpha]);
    }
  }

  initialState = inputs[index];
  index++;
  temp = stoi(inputs[index]);
  index++;
  finalStates.resize(m, false);
  while (temp--) {
    string tempS = inputs[index];
    finalStates[stateIndex[tempS]] = true;
    index++;
  }

   // Print transition table
    cout << "Transition Table:\n";
    for (auto& row : transitions) {
        for (auto& col : row) {
            for (auto& val : col) {
                cout << val << " ";
            }
            cout << " | ";
        }
        cout << endl;
    }

    //check the input string
  bool valid = false;

  string s;
  std::cout << "Enter the string that needs to be matched\n";
  std::cin >> s;
  //stack for initilizing the current 
  stack<int> stk;
  stk.push(alphaIndex[initialState]);
  for (int i = 0; i < s.length(); ++i) {
    vector<int> next;
    string currChar;
    currChar.push_back(s[i]);
    while (!stk.empty()) {
      int current = stk.top();
      stk.pop();
      for (int i = 0; i < m; ++i) {
        for (auto x : transitions[current][i])
          if (x == alphaIndex[currChar]) {
            next.push_back(i);
          }
      }
    }

    std::cout << "Intermediate Step " << i + 1 << ":\n";
    std::cout << "Next states: ";
    for (auto x : next) {
      std::cout << states[x] << " ";
    }
    std::cout << "\n\n";

    for (auto x : next) {
      stk.push(x);
    }
    if (i == s.length() - 1) {
      for (auto x : next)
        valid = valid | finalStates[x];
    }
  }

  std::cout << "Final result:\n";
  while (!stk.empty()) {
    int curr = stk.top();
    stk.pop();
    valid = valid | finalStates[curr];
  }

  if (valid) {
    std::cout << "Valid string\n";

  } else {
    std::cout << "Invalid string\n";
  }

}