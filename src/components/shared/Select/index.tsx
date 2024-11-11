import { SelectOption } from "@/components/shared/Select/type.ts";

type SelectProps = {
  label?: string;
  options: SelectOption[];
  className?: string;
}

export const Select = ({ className, label, options }: SelectProps) => {
  return <form className={className}>
    { label && <label htmlFor="countries" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">{label}</label> }
    <select id="countries" className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-4 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
      {
        options.map(option => (<option key={option.id} value={option.id} selected={option.selected}>{option.label}</option>))
      }
    </select>
  </form>;
};