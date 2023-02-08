import { carList } from '@/constants/carList';
import { Store } from '@/core/Store.js';
interface IOptionState {
  carModal: string | null;
  rideTogether: boolean;
  options: IOption[];
}
interface IOption {
  name: string;
  category: string;
}
interface DynamicObject {
  [property: string]: any;
}
const initState = {
  carModel: carList[0].title,
  rideTogether: false,
  options: [],
};

const reducer = (
  state: IOptionState,
  actionKey: string,
  payload: DynamicObject = {}
) => {
  const newState: IOptionState = JSON.parse(JSON.stringify(state));

  switch (actionKey) {
    case 'OPTION_INIT':
      return initState;

    case 'ADD_CAR_OPTION':
      newState.options.push(payload.option);
      return newState;

    case 'DELETE_CAR_OPTION':
      newState.options = newState.options.filter(
        (ele) => JSON.stringify(ele) !== JSON.stringify(payload.option)
      );
      return newState;

    case 'SELECT_CAR_MODEL':
      return { ...newState, carModel: payload.name };

    default:
      return { ...state };
  }
};

export const OptionStore = new Store(initState, reducer);
