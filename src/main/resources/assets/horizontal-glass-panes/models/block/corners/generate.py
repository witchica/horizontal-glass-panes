colors = [
    "white",
    "orange",
    "magenta",
    "light_blue",
    "yellow",
    "lime",
    "pink",
    "gray",
    "light_gray",
    "cyan",
    "purple",
    "blue",
    "brown",
    "green",
    "red",
    "black"
]

north = """
    {{
        "parent": "horizontal-glass-panes:block/pane_c_north",
        "textures": {{
            "pane": "block/{0}_stained_glass",
            "side": "block/{0}_stained_glass_pane_top",
            "particle": "block/{0}_stained_glass"
        }}
    }}
"""

north_offset = """
    {{
        "parent": "horizontal-glass-panes:block/pane_c_north_offset",
        "textures": {{
            "pane": "block/{0}_stained_glass",
            "side": "block/{0}_stained_glass_pane_top",
            "particle": "block/{0}_stained_glass"
        }}
    }}
"""

corner = """
    {{
        "parent": "horizontal-glass-panes:block/pane_c_corner",
        "textures": {{
            "pane": "block/{0}_stained_glass",
            "side": "block/{0}_stained_glass_pane_top",
            "particle": "block/{0}_stained_glass"
        }}
    }}
"""

for color in colors:
    f = open("pane_{0}_corner.json".format(color), "w")
    f.write(corner.format(color))
    f.close()

for color in colors:
    f = open("pane_{0}_north.json".format(color), "w")
    f.write(north.format(color))
    f.close()

for color in colors:
    f = open("pane_{0}_north_offset.json".format(color), "w")
    f.write(north_offset.format(color))
    f.close()