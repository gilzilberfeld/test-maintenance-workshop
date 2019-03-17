using System;
using System.Linq;

namespace CalculatorServer
{
    public class Calculator : ICalculator
    {
        static string display = "";
        static int lastArgument = 0;
        static bool newArgument = false;
        static bool shouldReset = true;

        static OperationType lastOperation;
        private readonly UserDbContext db;

        public Calculator(UserDbContext db)
        {
            this.db = db;
        }

    public void Press(String key)
        {
            if (key.Equals("C"))
                display = "";
            else
            {
                if (key.Equals("+"))
                {
                    lastOperation = OperationType.Plus;
                    lastArgument = Int32.Parse(display);
                    newArgument = true;
                }
                else
                {
                    if (key.Equals("/"))
                    {
                        lastOperation = OperationType.Div;
                        lastArgument = Int32.Parse(display);
                        newArgument = true;
                    }
                    else if (key.Equals("="))
                    {
                        int currentArgument = Int32.Parse(display);
                        if (lastOperation == OperationType.Plus)
                        {
                            display = (lastArgument + currentArgument).ToString();
                        }
                        if (lastOperation == OperationType.Div && currentArgument == 0)
                        {
                            display = "Division By Zero Error";
                        }
                        shouldReset = true;
                    }
                    else
                    {
                        if (shouldReset)
                        {
                            display = "";
                            shouldReset = false;
                        }
                        if (newArgument)
                        {
                            display = "";
                            newArgument = false;
                        }
                        display += key;
                    }
                }
            }
        }


        public String Display
        {
            get
            {
                if (display.Equals(""))
                    return "0";
                if (display.Length > 5)
                    return "E";
                return display;
            }
        }


        public void GetLastValueFor(String userName)
        {
            User user = GetUserByName(userName);
            if (user != null)
                display = user.Memory.ToString();
        }

        // @Autowired
        //public UserRepository userRepository;
        private User GetUserByName(String userName)
        {
            //     User criterion = new User();
            //     criterion.setName(userName);
            //     criterion.setMemory(null);

            //     Example<User> filter = Example.of(criterion);
            //     Optional<User> user = userRepository.findOne(filter);
            //     if (user.isPresent())
            //         return user.get();
            //     else
            return null;
        }

        public static void Reset()
        {
            display = "";
            lastArgument = 0;
        }
    }

}