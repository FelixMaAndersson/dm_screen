import { useState } from "react";
import { createMonster } from "../api/monstersApi";
import type {
    MonsterType,
    CreatureSize,
    Alignment,
    Habitat,
    ChallengeRating
} from "../types/enums";
import * as React from "react";

function CreateMonsterForm() {
    const [name, setName] = useState("");
    const [cr, setCr] = useState<ChallengeRating>("CR_0");
    const [xp, setXp] = useState("");
    const [type, setType] = useState<MonsterType>("BEAST");
    const [size, setSize] = useState<CreatureSize>("MEDIUM");
    const [alignment, setAlignment] = useState<Alignment>("ANY_ALIGNMENT");
    const [habitat, setHabitat] = useState<Habitat>("FOREST");
    const [hp, setHp] = useState("");
    const [tag, setTag] = useState("");

    async function handleSubmit(event: React.SubmitEvent) {
        event.preventDefault();

        await createMonster({
            name,
            cr,
            xp: Number(xp),
            type,
            size,
            alignment,
            habitat,
            hp: Number(hp),
            tag,
        });

        setName("");
        setCr("CR_0");
        setXp("");
        setType("BEAST");
        setSize("MEDIUM");
        setAlignment("ANY_ALIGNMENT");
        setHabitat("FOREST");
        setHp("");
        setTag("");
    }

    return (
        <form onSubmit={handleSubmit}>
            <h2>Create monster</h2>

            <div>
                <label>Name</label>
                <input value={name} onChange={e => setName(e.target.value)} />
            </div>

            <div>
                <label>CR</label>
                <select value={cr} onChange={e => setCr(e.target.value as ChallengeRating)}>
                    <option value="CR_0">0</option>
                    <option value="CR_1_8">1/8</option>
                    <option value="CR_1_4">1/4</option>
                    <option value="CR_1_2">1/2</option>
                    <option value="CR_1">1</option>
                    <option value="CR_2">2</option>
                    <option value="CR_3">3</option>
                    <option value="CR_4">4</option>
                    <option value="CR_5">5</option>
                    <option value="CR_6">6</option>
                    <option value="CR_7">7</option>
                    <option value="CR_8">8</option>
                    <option value="CR_9">9</option>
                    <option value="CR_10">10</option>
                    <option value="CR_11">11</option>
                    <option value="CR_12">12</option>
                    <option value="CR_13">13</option>
                    <option value="CR_14">14</option>
                    <option value="CR_15">15</option>
                    <option value="CR_16">16</option>
                    <option value="CR_17">17</option>
                    <option value="CR_18">18</option>
                    <option value="CR_19">19</option>
                    <option value="CR_20">20</option>
                    <option value="CR_21">21</option>
                    <option value="CR_22">22</option>
                    <option value="CR_23">23</option>
                    <option value="CR_24">24</option>
                    <option value="CR_25">25</option>
                    <option value="CR_26">26</option>
                    <option value="CR_27">27</option>
                    <option value="CR_28">28</option>
                    <option value="CR_29">29</option>
                    <option value="CR_30">30</option>
                </select>
            </div>

            <div>
                <label>XP</label>
                <input type="number" value={xp} onChange={e => setXp(e.target.value)} />
            </div>

            <div>
                <label>Type</label>
                <select value={type} onChange={e => setType(e.target.value as MonsterType)}>
                    <option value="ABERRATION">Aberration</option>
                    <option value="BEAST">Beast</option>
                    <option value="CELESTIAL">Celestial</option>
                    <option value="CONSTRUCT">Construct</option>
                    <option value="DRAGON">Dragon</option>
                    <option value="ELEMENTAL">Elemental</option>
                    <option value="FEY">Fey</option>
                    <option value="FIEND">Fiend</option>
                    <option value="GIANT">Giant</option>
                    <option value="HUMANOID">Humanoid</option>
                    <option value="MONSTROSITY">Monstrosity</option>
                    <option value="OOZE">Ooze</option>
                    <option value="PLANT">Plant</option>
                    <option value="UNDEAD">Undead</option>
                </select>
            </div>

            <div>
                <label>Size</label>
                <select value={size} onChange={e => setSize(e.target.value as CreatureSize)}>
                    <option value="TINY">Tiny</option>
                    <option value="SMALL">Small</option>
                    <option value="MEDIUM">Medium</option>
                    <option value="LARGE">Large</option>
                    <option value="HUGE">Huge</option>
                    <option value="GARGANTUAN">Gargantuan</option>
                </select>
            </div>

            <div>
                <label>Alignment</label>
                <select value={alignment} onChange={e => setAlignment(e.target.value as Alignment)}>
                    <option value="ANY_ALIGNMENT">Any alignment</option>
                    <option value="LAWFUL_GOOD">Lawful good</option>
                    <option value="NEUTRAL_GOOD">Neutral good</option>
                    <option value="CHAOTIC_GOOD">Chaotic good</option>
                    <option value="LAWFUL_NEUTRAL">Lawful neutral</option>
                    <option value="TRUE_NEUTRAL">True neutral</option>
                    <option value="CHAOTIC_NEUTRAL">Chaotic neutral</option>
                    <option value="LAWFUL_EVIL">Lawful evil</option>
                    <option value="NEUTRAL_EVIL">Neutral evil</option>
                    <option value="CHAOTIC_EVIL">Chaotic evil</option>
                </select>
            </div>

            <div>
                <label>Habitat</label>
                <select value={habitat} onChange={e => setHabitat(e.target.value as Habitat)}>
                    <option value="ARCTIC">Arctic</option>
                    <option value="COASTAL">Coastal</option>
                    <option value="DESERT">Desert</option>
                    <option value="FOREST">Forest</option>
                    <option value="GRASSLAND">Grassland</option>
                    <option value="HILL">Hill</option>
                    <option value="MOUNTAIN">Mountain</option>
                    <option value="SWAMP">Swamp</option>
                    <option value="UNDERDARK">Underdark</option>
                    <option value="UNDERWATER">Underwater</option>
                    <option value="URBAN">Urban</option>
                </select>
            </div>

            <div>
                <label>HP</label>
                <input type="number" value={hp} onChange={e => setHp(e.target.value)} />
            </div>

            <div>
                <label>Tag</label>
                <input value={tag} onChange={e => setTag(e.target.value)} />
            </div>

            <button type="submit">Create monster</button>
        </form>
    );
}

export default CreateMonsterForm;