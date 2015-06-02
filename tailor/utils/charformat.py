
def is_upper_camel_case(word):
    return word and (not '_' in word) and word[0].isupper()

def is_lower_camel_case(word):
    return word and (not '_' in word) and word[0].islower()
