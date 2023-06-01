#include <bits/stdc++.h>

using namespace std;

class LCS
{
public:
    int algorithm(string s1,string s2,int n,int m,string &ans)
    {
        if(n==0 || m==0)
        return 0;

        if(s1[n-1]==s2[m-1])
        {
            return 1+algorithm(s1,s2,n-1,m-1,ans);
        }
        else
        {
            return max(algorithm(s1,s2,n-1,m,ans),algorithm(s1,s2,n,m-1,ans));
        }
    }
};
int main()
{
    string s1;
    string s2;
    int n;
    int m;
    int lns=0;
    string ans="";
    cout<<"Enter the first string: ";
    cin>>s1;
    cout<<"Enter the second string: ";
    cin>>s2;
    LCS *l=new LCS();
    lns=l->algorithm(s1,s2,s1.length(),s2.length(),ans);
    cout<<"ANS lenght:- "<<lns<<endl;
    cout<<"ANS string:- "<<ans;
    return 0;
}


